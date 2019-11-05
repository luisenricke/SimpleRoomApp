package com.luisenricke.simpleroomapp

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.luisenricke.simpleroomapp.utils.Permission
import timber.log.Timber


abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var permission: Permission

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun isHasPermission(permissions: Array<String>): Boolean {
        val grantedPermission: Array<Boolean>
        for (isGranted in permissions.indices) {
            if (ContextCompat.checkSelfPermission(
                        this,
                        permissions[isGranted]
                    ) == PackageManager.PERMISSION_GRANTED) {

            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flag = true
            for (permission in permissions.iterator())
                flag = checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
            return flag
        }
        return true
    }

    protected fun askPermission(requestCode: Int = 0, permissions: Array<String>) {
        ActivityCompat.requestPermissions(this, permissions, requestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        Timber.i(" permission: ${permissions.size} grantresult: ${grantResults.size}")
        var flag = true

        if (grantResults.isEmpty())
            Timber.i("Empty results").run { return }

        //if (grantResults[0] == PackageManager.PERMISSION_DENIED)
        //    Timber.i("Permission denied")

        if (requestCode == permission.requestCode) {

            val checker = HashMap<String, Int>()
            for (index in permissions.indices) {
                Timber.i("permission: ${permissions[index]}")
            }

            for (index in permissions.indices) {
                if (grantResults[index] == PackageManager.PERMISSION_DENIED) {
                    checker[permissions[index]] = grantResults[index]
                    Timber.i("Key: ${permissions[index]} - value: ${checker[permissions[index]]}")
                }
            }

            if (checker.values.contains(PackageManager.PERMISSION_DENIED)) {
                Timber.i("Some permission dont granted")
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    askPermission(requestCode, checker.keys.toTypedArray())
                }
            }

/*
            for (index in permissions.indices) {
                if (grantResults[index] == PackageManager.PERMISSION_DENIED) {
                    askPermission(requestCode,permission.manifest.toTypedArray())
                    flag = false
                    break
                }
            }

            Timber.i("Trigger permission")
            if (flag) startActivityForResult(permission.config(), permission.requestCode)
            else Timber.i("We dont have permission to make the action requested")
*/
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK)
            Timber.i("Something failed").run { return }

        if (requestCode == permission.requestCode) permission.success(contentResolver, data)
    }
}