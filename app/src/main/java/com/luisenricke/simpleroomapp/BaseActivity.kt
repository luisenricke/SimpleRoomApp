package com.luisenricke.simpleroomapp

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.luisenricke.simpleroomapp.utils.Permission
import timber.log.Timber


abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var perm: Permission

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun isHasPermission(vararg permissions: String): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var permissionFlag = true
            for (singlePermission in permissions) {
                permissionFlag =
                    applicationContext.checkSelfPermission(singlePermission) == PackageManager.PERMISSION_GRANTED
            }
            return permissionFlag
        }
        return true
    }

    protected fun askPermission(requestCode: Int = -1, vararg permissions: String) {
        ActivityCompat.requestPermissions(this, permissions, requestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
            Timber.i("Permission denied")
            return
        }

        if (requestCode == perm.CODE_PERMISSION)
            startActivityForResult(perm.config(), perm.CODE_REQUEST)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            Timber.i("Something failed")
            return
        }

        if (requestCode == perm.CODE_REQUEST) perm.success(contentResolver, data)
    }
}