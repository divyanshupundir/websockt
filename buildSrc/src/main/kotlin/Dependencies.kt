object Deps {

    const val okhttp = "com.squareup.okhttp3:okhttp:4.10.0"
}

object TestDeps {

    object Jupiter {
        private const val version = "5.8.2"
        const val api = "org.junit.jupiter:junit-jupiter-api:$version"
        const val engine = "org.junit.jupiter:junit-jupiter-engine:$version"
    }
}
