package com.android.feature.album

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.feature.album.R


@Preview
@Composable
fun AlbumScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        val painterResource = painterResource(R.drawable.ic_paint_vector)
        Image(
            painter = painterResource,
            contentDescription = "",
            modifier = Modifier
                .wrapContentSize()
                .align(alignment = CenterHorizontally)
        )
        
        Spacer(modifier = Modifier.width(16.dp))

        LazyColumn {

        }
    }
}

