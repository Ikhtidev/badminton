package uz.azimov.badminton.utils

import uz.azimov.badminton.models.Chapter
import uz.azimov.badminton.models.Theme

object Constants {
    val CHAPTERS_WITH_TOPICS: List<Chapter> = listOf(
        Chapter(
            id = "modul_1",
            title = "BADMINTON FANIGA KIRISH",
            topics = listOf(
                Theme(
                    "Badmınton fanining maqsadi va vazifalari",
                    "1.1"
                ),
                Theme(
                    "Ta’lim muassasalarida badminton fanining dolzarbligi",
                    "1.2"
                ),
                Theme(
                    "Fan bo‘yicha talabalarning bilim, ko‘nikma va malakalariga qo‘yiladigan talablar",
                    "1.3"
                ),
            )
        ),
        Chapter(
            id = "modul_2",
            title = "BADMINTON SPORT TURINING KELIB CHIQISHI VA TARIXI",
            topics = listOf(
                Theme(
                    "Badmintonning rivojlanish tarixi va bosqichlari",
                    "2.1"
                ),
                Theme(
                    "O‘zbekistonda badmintonning rivojlanishi va bugungi istiqbollari",
                    "2.2"
                ),
                Theme(
                    "Badminton o‘yinining xalqarosport olamidagi nufuzi va o‘rni",
                    "2.3"
                ),
            )
        ),
        Chapter(
            id = "modul_3",
            title = "BADMINTON O‘YIN QONUN QOIDALARI",
            topics = listOf(
                Theme(
                    "O‘yin maydoni o‘lchamlari, raketka hamda volan xususiyatlari",
                    "3.1"
                ),
                Theme(
                    "Badminton o‘yin qoidalari.",
                    "3.2"
                ),
                Theme(
                    "O‘yin hakamlari tuzilmasi.",
                    "3.3"
                ),
                Theme(
                    "Badmintonda musobaqalar tashkil etish va o‘tkazish",
                    "3.4"
                ),
            )
        ),
        Chapter(
            id = "modul_4",
            title = "O‘YIN TEXNIKASI VA TAKTIKASI, ULARNI TASNIFI",
            topics = listOf(
                Theme(
                    "Badminton o‘yin texnikasi tavsifi",
                    "4.1"
                ),
                Theme(
                    "Badminton o‘yin taktikasi tavsifi ",
                    "4.2"
                ),
                Theme(
                    "O‘yindagi hujum va zarba usullari",
                    "4.3"
                ),
                Theme(
                    "Yakkalik, juftlik hamda aralash juftlik (mikst) o‘yinlar",
                    "4.4"
                ),
            )
        ),
        Chapter(
            id = "modul_5",
            title = "BADMINTONCHILARNING JISMONIY TAYYORGARLIGINI RIVOJLANTIRISH USLUBIYOTI",
            topics = listOf(
                Theme(
                    "Yosh badmintonchilarning umumiy jismoniy tayyorgarligi.",
                    "5.1"
                ),
                Theme(
                    "O‘yindagi tayyorgarlikning xususiyatlari va mazmuni.",
                    "5.2"
                ),
                Theme(
                    "Badmintonchilarning jismoniy sifatlarini tarbiyalash.",
                    "5.3"
                ),
                Theme(
                    "Yosh badmintonchilarning sport mashg‘ulotlari uslubiyoti",
                    "5.4"
                ),
                Theme(
                    "Badminton sport turiga xos harakatli o‘yinlar.",
                    "5.5"
                ),
            )
        )
    )
}