package com.example.gridapp

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gridapp.data.DataSource
import com.example.gridapp.model.Topic
import com.example.gridapp.ui.theme.GridAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GridApp()
                }
            }
        }
    }
}

@Composable
fun GridApp(modifier: Modifier = Modifier) {
    TopicGrid()
}

@Composable
fun TopicGrid(modifier: Modifier = Modifier){
    val myTopicList = DataSource().topics
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
        )
    {
        items(myTopicList) {topic ->
            GridCard(topic = topic)

        }
    }
}

@Composable
fun GridCard(topic: Topic,modifier: Modifier = Modifier) {
   Card (
       modifier = modifier
   ) {
    Row  {
        Box {
            Image(
                painter = painterResource(id = topic.imageResourceId),
                contentDescription = stringResource(topic.stringResourceId),
                modifier = Modifier
                    .size(width = 68.dp, height = 68.dp)
                    .fillMaxHeight()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
        }
        Column(

        ) {
            Text(
                text = stringResource(topic.stringResourceId),
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp,
                        ),
                style = MaterialTheme.typography.bodyMedium
            )
            Row (
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Icon(
                    painter = painterResource(R.drawable.ic_grain),
                    contentDescription = stringResource(R.string.grain),
                    modifier = Modifier
                        .padding
                            (
                        start = 16.dp, end = 8.dp
                    )
                )
                Text(
                    text = topic.topicNumber.toString(),
                    style = MaterialTheme.typography.labelMedium
                    )
            }
        }
    }
   }
}

@Preview(showBackground = true)
@Composable
fun GridAppPreview() {
    GridAppTheme {
        TopicGrid()
    }
}