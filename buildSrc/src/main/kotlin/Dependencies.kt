object Deps {

    object ReactiveX {
        const val rxJava2 = "io.reactivex.rxjava2:rxjava:2.2.21"
        const val rxJava3 = "io.reactivex.rxjava3:rxjava:3.1.6"
    }

    const val okhttp = "com.squareup.okhttp3:okhttp:4.10.0"
}

object TestDeps {

    object Jupiter {
        private const val version = "5.8.2"
        const val api = "org.junit.jupiter:junit-jupiter-api:$version"
        const val engine = "org.junit.jupiter:junit-jupiter-engine:$version"
    }
}
