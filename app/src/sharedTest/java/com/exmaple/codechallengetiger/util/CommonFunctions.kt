package com.exmaple.codechallengetiger.util

import com.example.codechallengetiger.model.Job

fun getFakeJobList(): List<Job> {
    val list = arrayListOf<Job>()
    for (i in 0 until 10) {
        list.add(
            Job(
                "Name $i",
                i.toString(),
                "1.1.2019",
                "",
                3.3,
                "14:12",
                "Cash",
                32.3,
                4,
                "Berlin",
                13000,
                "Alexander Platz",
                "Completed"
            )
        )
    }

    return list
}

fun getFakeStaleJobList(): List<Job> {
    val list = arrayListOf<Job>()
    for (i in 10 until 20) {
        list.add(
            Job(
                "Name $i",
                i.toString(),
                "1.1.2019",
                "",
                3.3,
                "14:12",
                "Cash",
                32.3,
                4,
                "Berlin",
                13000,
                "Alexander Platz",
                "Completed"
            )
        )
    }

    return list
}