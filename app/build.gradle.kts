plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.david.appprojet"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.david.appprojet"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    /*On ajoute la bibliothèque Jackson pour le traitement de documentation JSON*/
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    /*On ajoute la bibliothèque OkHttp pour la communication avec le serveur en Java*/
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    // https://mvnrepository.com/artifact/mysql/mysql-connector-java
    implementation("mysql:mysql-connector-java:8.0.33")

}