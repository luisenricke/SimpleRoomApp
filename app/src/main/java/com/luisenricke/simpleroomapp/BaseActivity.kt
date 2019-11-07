package com.luisenricke.simpleroomapp

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.luisenricke.simpleroomapp.utils.Permission
import timber.log.Timber


abstract class BaseActivity : AppCompatActivity() {

    private var permObject: Permission? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun checkPermission(permission: Permission)/*: Boolean*/ {
        permObject = permission
        var existDenial = false
        for (index in permission.manifest.indices) {
            val checkPermission = isPermissionGranted(permission.manifest.elementAt(index))
            Timber.i("Check permission: ${permission.manifest.elementAt(index)} -> $checkPermission")
            if (!checkPermission) {
                existDenial = true
                break
            }
        }

        if (existDenial)
            ActivityCompat.requestPermissions(this, permission.manifest.toTypedArray(), permission.requestCode)
        else
            startActivityForResult(permission.config(), permission.requestCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isEmpty()) Timber.i("Empty results").run { return }
        if (requestCode != permObject?.requestCode) Timber.i("Doesn't match request code").run { return }

        val listNotAsked: ArrayList<String> = arrayListOf()
        var countDenied = 0

        for (index in permissions.indices) {
            Timber.i("permission: ${permissions[index]}")
            if (grantResults[index] == PackageManager.PERMISSION_DENIED) {
                countDenied++
                Timber.i("--> DENIED")
                if (isPermissionCheckNeverAskAgain(permissions[index])) {
                    listNotAsked.add("* " + permissions[index].removePrefix("android.permission.").replace("_"," "))
                    Timber.i("----> Never ask again")
                }
            }
        }

        if (listNotAsked.size == countDenied && countDenied != 0) {
            MaterialAlertDialogBuilder(this)
                .setTitle("Petición de permisos")
                .setIcon(R.drawable.ic_settings_black_24dp)
                .setMessage("La aplicación tiene unos permisos desabilitados que son requeridos para esta funcionalidad ¿Desea activarlos? \n${listNotAsked.toStringMultiLine()}")
                .setPositiveButton("Si") { _, _ -> settings() }
                .setNegativeButton("No") { _, _ -> Toast.makeText(this, "La operación no se puedo realizar", Toast.LENGTH_LONG).show() }
                .show()
            return
        }

        if (countDenied == 0)
            startActivityForResult(permObject?.config(), permObject?.requestCode!!)
        else
            Toast.makeText(this, "No se tiene los permisos necesarios para realizar esta accion", Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) Timber.i("Something went wrong or the operation was cancelled").run { return }

        if (requestCode == permObject?.requestCode) permObject?.success(contentResolver, data)

        permObject = null
    }

    private fun isPermissionGranted(permission: String): Boolean =
        ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

    private fun isPermissionCheckNeverAskAgain(permission: String): Boolean =
        !ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

    private fun settings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", packageName, null))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun ArrayList<String>.toStringMultiLine(): String {
        var aux = ""
        for (element in this) {
            aux += element + "\n"
        }
        return aux
    }
}