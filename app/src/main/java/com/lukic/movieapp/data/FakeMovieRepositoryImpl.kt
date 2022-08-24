package com.lukic.movieapp.data

import com.lukic.movieapp.domain.model.Cast
import com.lukic.movieapp.domain.model.Credits
import com.lukic.movieapp.domain.model.Movie
import com.lukic.movieapp.domain.repository.MovieRepository

private const val MOVIE_THUMBNAIL_URL = "https://m.media-amazon.com/images/M/MV5BOTUwODM5MTctZjczMi00OTk4LTg3NWUtNmVhMTAzNTNjYjcyXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX128_CR0,3,128,176_AL_.jpg"
private const val CAST_THUMBNAIL_URL = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hPwCMEq6jLAidsXAX5BfoYgIfg2.jpg"

class FakeMovieRepositoryImpl : MovieRepository {

    override fun getMovieByID(id: String): Movie? = Movies.movies.firstOrNull { movie -> movie.id == id }

    override fun getAllMovies(): List<Movie> = Movies.movies

    private object Movies {
        val movies = listOf(
            Movie(
                title = "Top Gun: Maverick (2022)",
                overview = "Nakon više od trideset godina služenja kao jedan od najboljih pilota mornarice, Pete \"Maverick\" Mitchell je tamo gdje pripada, izbjegava napredovanje u čin koji bi ga \"prizemljio\". Kad se nađe kako obučava odred mladih pilota Top Gun-a za specijaliziranu misiju, kakvu još niti jedan pilot nije vidio, Maverick susreće Bradleyja Bradshawa, sina svog pokojnog prijatelja, Nicka Bradshawa, zvanog \"Goose\". Suočavajući se s neizvjesnom budućnošću i s duhovima svoje prošlosti, Maverick se uvlači u sukob s vlastitim najdubljim strahovima...",
                genres = listOf("Action", "Drama"),
                rating = 83,
                credits = listOf(
                    Credits(
                        name = "Jim Cash",
                        role = "Characters"
                    ),
                    Credits(
                        name = "Jack Epps Jr",
                        role = "Characters"
                    ),
                    Credits(
                        name = "Joseph Kosinski",
                        role = "Director"
                    ),
                    Credits(
                        name = "Ehren Kruger",
                        role = "Screenplay"
                    ),
                    Credits(
                        name = "Jim Cash",
                        role = "Characters"
                    ),
                    Credits(
                        name = "Jack Epps Jr",
                        role = "Characters"
                    ),
                    Credits(
                        name = "Joseph Kosinski",
                        role = "Director"
                    ),
                    Credits(
                        name = "Ehren Kruger",
                        role = "Screenplay"
                    ),
                ),
                releaseDate = "27/05/2022 (US)",
                duration = "2h 11m",
                movieThumbnail = MOVIE_THUMBNAIL_URL,
                cast = listOf(
                    Cast(
                        name = "Tom Cruise",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Capt. Pete Mitchell"
                    ),
                    Cast(
                        name = "Miles Teller",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Lt. Bradley Bradshaw"
                    ),
                    Cast(
                        name = "Jennifer Connely",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Penny Benjamin"
                    ),
                    Cast(
                        name = "Jon Hamm",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Adm. Beau 'Cyclone' Simpson"
                    ),
                    Cast(
                        name = "Glen Powell",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Lt. Jake 'Hangman' Seresin"
                    ),
                    Cast(
                        name = "Ed Harris",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Radm. Chester 'Hammer' Cain"
                    )
                )
            ),
            Movie(
                title = "Dnevna smjena (2022)",
                overview = "Lovac na vampire iz L. A.-a ima tjedan dana da skupi lovu za kćerinu školarinu i aparatić za zube. Zarađivanje za život moglo bi ga na kraju stajati života!",
                genres = listOf("Action", "Fantasy", "Horror"),
                rating = 67,
                movieThumbnail = MOVIE_THUMBNAIL_URL,
                credits = listOf(
                    Credits(
                        name = "Tyler Tice",
                        role = "Screenplay, Story"
                    ),
                    Credits(
                        name = "J.J. Perry",
                        role = "Director"
                    ),
                    Credits(
                        name = "Shay Hatten",
                        role = "Screenplay"
                    ),
                    Credits(
                        name = "Ehren Kruger",
                        role = "Screenplay"
                    ),
                    Credits(
                        name = "Jim Cash",
                        role = "Characters"
                    ),
                    Credits(
                        name = "Jack Epps Jr",
                        role = "Characters"
                    ),
                    Credits(
                        name = "Joseph Kosinski",
                        role = "Director"
                    ),
                    Credits(
                        name = "Ehren Kruger",
                        role = "Screenplay"
                    ),

                ),
                releaseDate = "27/05/2022 (US)",
                duration = "2h 11m",
                cast = listOf(
                    Cast(
                        name = "Tom Cruise",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Capt. Pete Mitchell"
                    ),
                    Cast(
                        name = "Miles Teller",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Lt. Bradley Bradshaw"
                    ),
                    Cast(
                        name = "Jennifer Connely",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Penny Benjamin"
                    ),
                    Cast(
                        name = "Jon Hamm",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Adm. Beau 'Cyclone' Simpson"
                    ),
                    Cast(
                        name = "Glen Powell",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Lt. Jake 'Hangman' Seresin"
                    ),
                    Cast(
                        name = "Ed Harris",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Radm. Chester 'Hammer' Cain"
                    )
                )
            ),
            Movie(
                title = "Spider-Man: No Way Home (2021)",
                overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                genres = listOf("Action", "Adventure", "Science", "Fiction"),
                rating = 80,
                movieThumbnail = MOVIE_THUMBNAIL_URL,
                credits = listOf(
                    Credits(
                        name = "Stan Lee",
                        role = "Characters"
                    ),
                    Credits(
                        name = "Steve Ditko",
                        role = "Characters"
                    ),
                    Credits(
                        name = "Jon Watts",
                        role = "Director"
                    ),
                    Credits(
                        name = "Chris McKenna",
                        role = "Writer"
                    ),
                    Credits(
                        name = "Erik Sommers",
                        role = "Writer"
                    )
                ),
                releaseDate = "17/12/2021 (US)",
                duration = "2h 28m",
                cast = listOf(
                    Cast(
                        name = "Tom Cruise",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Capt. Pete Mitchell"
                    ),
                    Cast(
                        name = "Miles Teller",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Lt. Bradley Bradshaw"
                    ),
                    Cast(
                        name = "Jennifer Connely",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Penny Benjamin"
                    ),
                    Cast(
                        name = "Jon Hamm",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Adm. Beau 'Cyclone' Simpson"
                    ),
                    Cast(
                        name = "Glen Powell",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Lt. Jake 'Hangman' Seresin"
                    ),
                    Cast(
                        name = "Ed Harris",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Radm. Chester 'Hammer' Cain"
                    )
                )
            ),
            Movie(
                title = "Dnevna smjena (2022)",
                overview = "Lovac na vampire iz L. A.-a ima tjedan dana da skupi lovu za kćerinu školarinu i aparatić za zube. Zarađivanje za život moglo bi ga na kraju stajati života!",
                genres = listOf("Action", "Fantasy", "Horror"),
                rating = 67,
                movieThumbnail = MOVIE_THUMBNAIL_URL,
                credits = listOf(
                    Credits(
                        name = "Tyler Tice",
                        role = "Screenplay, Story"
                    ),
                    Credits(
                        name = "J.J. Perry",
                        role = "Director"
                    ),
                    Credits(
                        name = "Shay Hatten",
                        role = "Screenplay"
                    ),
                    Credits(
                        name = "Ehren Kruger",
                        role = "Screenplay"
                    ),
                    Credits(
                        name = "Jim Cash",
                        role = "Characters"
                    ),
                    Credits(
                        name = "Jack Epps Jr",
                        role = "Characters"
                    ),
                    Credits(
                        name = "Joseph Kosinski",
                        role = "Director"
                    ),
                    Credits(
                        name = "Ehren Kruger",
                        role = "Screenplay"
                    ),

                ),
                releaseDate = "27/05/2022 (US)",
                duration = "2h 11m",
                cast = listOf(
                    Cast(
                        name = "Tom Cruise",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Capt. Pete Mitchell"
                    ),
                    Cast(
                        name = "Miles Teller",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Lt. Bradley Bradshaw"
                    ),
                    Cast(
                        name = "Jennifer Connely",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Penny Benjamin"
                    ),
                    Cast(
                        name = "Jon Hamm",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Adm. Beau 'Cyclone' Simpson"
                    ),
                    Cast(
                        name = "Glen Powell",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Lt. Jake 'Hangman' Seresin"
                    ),
                    Cast(
                        name = "Ed Harris",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Radm. Chester 'Hammer' Cain"
                    )
                )
            ),
            Movie(
                title = "Top Gun: Maverick (2022)",
                overview = "Nakon više od trideset godina služenja kao jedan od najboljih pilota mornarice, Pete \"Maverick\" Mitchell je tamo gdje pripada, izbjegava napredovanje u čin koji bi ga \"prizemljio\". Kad se nađe kako obučava odred mladih pilota Top Gun-a za specijaliziranu misiju, kakvu još niti jedan pilot nije vidio, Maverick susreće Bradleyja Bradshawa, sina svog pokojnog prijatelja, Nicka Bradshawa, zvanog \"Goose\". Suočavajući se s neizvjesnom budućnošću i s duhovima svoje prošlosti, Maverick se uvlači u sukob s vlastitim najdubljim strahovima...",
                genres = listOf("Action", "Drama"),
                rating = 83,
                movieThumbnail = MOVIE_THUMBNAIL_URL,
                credits = listOf(
                    Credits(
                        name = "Jim Cash",
                        role = "Characters"
                    ),
                    Credits(
                        name = "Jack Epps Jr",
                        role = "Characters"
                    ),
                    Credits(
                        name = "Joseph Kosinski",
                        role = "Director"
                    ),
                    Credits(
                        name = "Ehren Kruger",
                        role = "Screenplay"
                    ),
                    Credits(
                        name = "Jim Cash",
                        role = "Characters"
                    ),
                    Credits(
                        name = "Jack Epps Jr",
                        role = "Characters"
                    ),
                    Credits(
                        name = "Joseph Kosinski",
                        role = "Director"
                    ),
                    Credits(
                        name = "Ehren Kruger",
                        role = "Screenplay"
                    ),

                ),
                releaseDate = "27/05/2022 (US)",
                duration = "2h 11m",
                cast = listOf(
                    Cast(
                        name = "Tom Cruise",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Capt. Pete Mitchell"
                    ),
                    Cast(
                        name = "Miles Teller",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Lt. Bradley Bradshaw"
                    ),
                    Cast(
                        name = "Jennifer Connely",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Penny Benjamin"
                    ),
                    Cast(
                        name = "Jon Hamm",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Adm. Beau 'Cyclone' Simpson"
                    ),
                    Cast(
                        name = "Glen Powell",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Lt. Jake 'Hangman' Seresin"
                    ),
                    Cast(
                        name = "Ed Harris",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Radm. Chester 'Hammer' Cain"
                    )
                )
            ),
            Movie(
                title = "Dnevna smjena (2022)",
                overview = "Lovac na vampire iz L. A.-a ima tjedan dana da skupi lovu za kćerinu školarinu i aparatić za zube. Zarađivanje za život moglo bi ga na kraju stajati života!",
                genres = listOf("Action", "Fantasy", "Horror"),
                rating = 67,
                movieThumbnail = MOVIE_THUMBNAIL_URL,
                credits = listOf(
                    Credits(
                        name = "Tyler Tice",
                        role = "Screenplay, Story"
                    ),
                    Credits(
                        name = "J.J. Perry",
                        role = "Director"
                    ),
                    Credits(
                        name = "Shay Hatten",
                        role = "Screenplay"
                    ),
                    Credits(
                        name = "Ehren Kruger",
                        role = "Screenplay"
                    ),
                    Credits(
                        name = "Jim Cash",
                        role = "Characters"
                    ),
                    Credits(
                        name = "Jack Epps Jr",
                        role = "Characters"
                    ),
                    Credits(
                        name = "Joseph Kosinski",
                        role = "Director"
                    ),
                    Credits(
                        name = "Ehren Kruger",
                        role = "Screenplay"
                    ),

                ),
                releaseDate = "27/05/2022 (US)",
                duration = "2h 11m",
                cast = listOf(
                    Cast(
                        name = "Tom Cruise",
                        roleName = "Capt. Pete Mitchell",
                        castThumbnail = CAST_THUMBNAIL_URL
                    ),
                    Cast(
                        name = "Miles Teller",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Lt. Bradley Bradshaw"
                    ),
                    Cast(
                        name = "Jennifer Connely",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Penny Benjamin"
                    ),
                    Cast(
                        name = "Jon Hamm",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Adm. Beau 'Cyclone' Simpson"
                    ),
                    Cast(
                        name = "Glen Powell",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Lt. Jake 'Hangman' Seresin"
                    ),
                    Cast(
                        name = "Ed Harris",
                        castThumbnail = CAST_THUMBNAIL_URL,
                        roleName = "Radm. Chester 'Hammer' Cain"
                    )
                )
            ),
        )
    }
}
