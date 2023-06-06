package com.lukic.movieapp.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.lukic.domain.model.Crew
import com.lukic.movieapp.R
import kotlinx.collections.immutable.ImmutableList

private const val REPEAT_COUNT = 3

@Composable
fun CrewMembersGrid(
    crew: ImmutableList<Crew>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.crew_column_spaced_by))
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.crew_row_spaced_by)),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            repeat(REPEAT_COUNT) { index ->
                if (crew.size > index) {
                    CrewMemberText(
                        crewMember = crew[index],
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.crew_row_spaced_by)),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            repeat(REPEAT_COUNT) { index ->
                if (crew.size > index + REPEAT_COUNT) {
                    CrewMemberText(
                        crewMember = crew[REPEAT_COUNT + index],
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun CrewMemberText(
    crewMember: Crew,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = crewMember.name,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colors.onSecondary,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
        Text(
            text = crewMember.role,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSecondary,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
    }
}
