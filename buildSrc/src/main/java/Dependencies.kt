@Suppress("unused")
object AndroidLib {

    object Version {
        const val appCompat = "1.1.0"
        const val ktx = "1.3.0"
        const val annotation = "1.1.0"
        const val legacy = "1.0.0"
    }

    const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Version.ktx}"
    const val annotationX = "androidx.annotation:annotation:${Version.annotation}"
    const val legacy = "androidx.legacy:legacy-support-v4:${Version.legacy}"
}

@Suppress("unused")
object ViewLib {

    object Version {
        const val materialDesign = "1.0.0"
        const val recyclerView = "1.1.0"
        const val cardView = "1.0.0"
        const val constraintLayout = "1.1.3"
    }

    const val materialDesign = "com.google.android.material:material:${Version.materialDesign}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Version.recyclerView}"
    const val cardView = "androidx.cardview:cardview:${Version.cardView}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
}

@Suppress("unused")
object RoomLib {

    object Version {
        const val room = "2.2.5"
    }

    const val runtime = "androidx.room:room-runtime:${Version.room}"
    const val compiler = "androidx.room:room-compiler:${Version.room}"
    const val annotation = "androidx.room:room-ktx:${Version.room}"
    const val rxJava = "androidx.room:room-rxjava2:${Version.room}"
    const val guava = "androidx.room:room-guava:${Version.room}"
    const val testing = "androidx.room:room-testing:${Version.room}"
}

@Suppress("unused")
object TestLib {

    object Version {
        const val junit = "4.13"
        const val junitExt = "1.1.1"
        const val mockitoCore = "3.3.3"
        const val testRunner = "1.2.0"
        const val espressoCore = "3.2.0"
    }

    const val junit = "junit:junit:${Version.junit}"
    const val junitExt = "androidx.test.ext:junit:${Version.junitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Version.espressoCore}"

    const val mockito = "org.mockito:mockito-core:${Version.mockitoCore}"
    const val runner = "androidx.test:runner:${Version.testRunner}"
}

@Suppress("unused")
object ToolLib {

    object Version {
        const val timber = "4.7.1"
    }

    const val timber = "com.jakewharton.timber:timber:${Version.timber}"
}
