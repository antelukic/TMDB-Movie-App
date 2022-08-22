package com.lukic.movieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.lukic.movieapp.adapters.MovieRecyclerAdapter
import com.lukic.movieapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var movieAdapter: MovieRecyclerAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        movieAdapter = MovieRecyclerAdapter(
            onClick = { title, view ->
                onItemClick(title, view)
            }
        )
        val movies = mutableListOf<String>()
        for (i in 0..20) {
            movies.add(i.toString())
        }
        movieAdapter.submitList(Movies.allMovies)

        with(binding) {
            homeFreeRv.adapter = movieAdapter
            homePopularRv.adapter = movieAdapter
            homeTrendingRv.adapter = movieAdapter
        }
        return binding.root
    }

    fun onItemClick(title: String, view: View) {
        val extras = FragmentNavigatorExtras(view to "details_image")
        findNavController().navigate(
            HomeFragmentDirections.actionHomePageFragmentToDetailsFragment(title),
            extras
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

/*Dummy data for testing*/
object Movies {
    val allMovies = listOf(
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
            cast = listOf(
                Cast(
                    name = "Tom Cruise",
                    roleName = "Capt. Pete Mitchell"
                ),
                Cast(
                    name = "Miles Teller",
                    roleName = "Lt. Bradley Bradshaw"
                ),
                Cast(
                    name = "Jennifer Connely",
                    roleName = "Penny Benjamin"
                ),
                Cast(
                    name = "Jon Hamm",
                    roleName = "Adm. Beau 'Cyclone' Simpson"
                ),
                Cast(
                    name = "Glen Powell",
                    roleName = "Lt. Jake 'Hangman' Seresin"
                ),
                Cast(
                    name = "Ed Harris",
                    roleName = "Radm. Chester 'Hammer' Cain"
                )
            )
        ),
        Movie(
            title = "Dnevna smjena (2022)",
            overview = "Lovac na vampire iz L. A.-a ima tjedan dana da skupi lovu za kćerinu školarinu i aparatić za zube. Zarađivanje za život moglo bi ga na kraju stajati života!",
            genres = listOf("Action", "Fantasy", "Horror"),
            rating = 67,
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
                    roleName = "Capt. Pete Mitchell"
                ),
                Cast(
                    name = "Miles Teller",
                    roleName = "Lt. Bradley Bradshaw"
                ),
                Cast(
                    name = "Jennifer Connely",
                    roleName = "Penny Benjamin"
                ),
                Cast(
                    name = "Jon Hamm",
                    roleName = "Adm. Beau 'Cyclone' Simpson"
                ),
                Cast(
                    name = "Glen Powell",
                    roleName = "Lt. Jake 'Hangman' Seresin"
                ),
                Cast(
                    name = "Ed Harris",
                    roleName = "Radm. Chester 'Hammer' Cain"
                )
            )
        ),
        Movie(
            title = "Spider-Man: No Way Home (2021)",
            overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            genres = listOf("Action", "Adventure", "Science", "Fiction"),
            rating = 80,
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
                    roleName = "Capt. Pete Mitchell"
                ),
                Cast(
                    name = "Miles Teller",
                    roleName = "Lt. Bradley Bradshaw"
                ),
                Cast(
                    name = "Jennifer Connely",
                    roleName = "Penny Benjamin"
                ),
                Cast(
                    name = "Jon Hamm",
                    roleName = "Adm. Beau 'Cyclone' Simpson"
                ),
                Cast(
                    name = "Glen Powell",
                    roleName = "Lt. Jake 'Hangman' Seresin"
                ),
                Cast(
                    name = "Ed Harris",
                    roleName = "Radm. Chester 'Hammer' Cain"
                )
            )
        ),
        Movie(
            title = "Dnevna smjena (2022)",
            overview = "Lovac na vampire iz L. A.-a ima tjedan dana da skupi lovu za kćerinu školarinu i aparatić za zube. Zarađivanje za život moglo bi ga na kraju stajati života!",
            genres = listOf("Action", "Fantasy", "Horror"),
            rating = 67,
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
                    roleName = "Capt. Pete Mitchell"
                ),
                Cast(
                    name = "Miles Teller",
                    roleName = "Lt. Bradley Bradshaw"
                ),
                Cast(
                    name = "Jennifer Connely",
                    roleName = "Penny Benjamin"
                ),
                Cast(
                    name = "Jon Hamm",
                    roleName = "Adm. Beau 'Cyclone' Simpson"
                ),
                Cast(
                    name = "Glen Powell",
                    roleName = "Lt. Jake 'Hangman' Seresin"
                ),
                Cast(
                    name = "Ed Harris",
                    roleName = "Radm. Chester 'Hammer' Cain"
                )
            )
        ),
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
            cast = listOf(
                Cast(
                    name = "Tom Cruise",
                    roleName = "Capt. Pete Mitchell"
                ),
                Cast(
                    name = "Miles Teller",
                    roleName = "Lt. Bradley Bradshaw"
                ),
                Cast(
                    name = "Jennifer Connely",
                    roleName = "Penny Benjamin"
                ),
                Cast(
                    name = "Jon Hamm",
                    roleName = "Adm. Beau 'Cyclone' Simpson"
                ),
                Cast(
                    name = "Glen Powell",
                    roleName = "Lt. Jake 'Hangman' Seresin"
                ),
                Cast(
                    name = "Ed Harris",
                    roleName = "Radm. Chester 'Hammer' Cain"
                )
            )
        ),
        Movie(
            title = "Dnevna smjena (2022)",
            overview = "Lovac na vampire iz L. A.-a ima tjedan dana da skupi lovu za kćerinu školarinu i aparatić za zube. Zarađivanje za život moglo bi ga na kraju stajati života!",
            genres = listOf("Action", "Fantasy", "Horror"),
            rating = 67,
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
                    roleName = "Capt. Pete Mitchell"
                ),
                Cast(
                    name = "Miles Teller",
                    roleName = "Lt. Bradley Bradshaw"
                ),
                Cast(
                    name = "Jennifer Connely",
                    roleName = "Penny Benjamin"
                ),
                Cast(
                    name = "Jon Hamm",
                    roleName = "Adm. Beau 'Cyclone' Simpson"
                ),
                Cast(
                    name = "Glen Powell",
                    roleName = "Lt. Jake 'Hangman' Seresin"
                ),
                Cast(
                    name = "Ed Harris",
                    roleName = "Radm. Chester 'Hammer' Cain"
                )
            )
        ),
    )
}
