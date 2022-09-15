package com.lukic.movieapp.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.lukic.movieapp.R

@Composable
fun HomeTabs(
    tabs: List<String>,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    edgePadding: Dp = dimensionResource(id = R.dimen.default_edge_padding),
    selectedContentColor: Color = colorResource(id = R.color.dark_blue_primary),
    defaultContentColor: Color = colorResource(id = R.color.gray_600),
    backgroundColor: Color = Color.Transparent,
    indicatorColor: Color = colorResource(id = R.color.dark_blue_primary)
) {
    var selectedIndex by remember { mutableStateOf(0) }
    ScrollableTabRow(
        modifier = modifier,
        selectedTabIndex = selectedIndex,
        edgePadding = edgePadding,
        backgroundColor = backgroundColor,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(
                    currentTabPosition = tabPositions[selectedIndex]
                ),
                color = indicatorColor
            )
        },
        divider = {
            TabRowDefaults.Divider(
                color = Color.Transparent
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    onTabClick(index)
                }
            ) {
                Text(
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.common_text_padding)),
                    text = tab,
                    color = if (selectedIndex == index) selectedContentColor
                    else defaultContentColor,
                    fontSize = dimensionResource(id = R.dimen.tab_text_size).value.sp,
                    fontFamily = FontFamily(Font(R.font.proxima_nova_alt_bold, FontWeight.Bold))
                )
            }
        }
    }
}
