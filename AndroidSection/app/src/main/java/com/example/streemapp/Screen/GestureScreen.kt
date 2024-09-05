package com.example.streemapp.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.streemapp.R

@Composable
fun LoadingScreen(modifier: Modifier){

    Image(painter = painterResource(
        R.drawable.loading_loading_forever),
        contentDescription = "Loading",
        modifier = modifier.size(200.dp)
    )
}

@Composable
fun ErrorScrren(retryAction:() ->Unit, modifier: Modifier = Modifier){

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(
                id=R.drawable.ic_connection_error),
            contentDescription ="Connection Error",
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = "Failed to load Data",
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = retryAction,
        ) {
            Text(text = "Retry")
        }
    }
}
