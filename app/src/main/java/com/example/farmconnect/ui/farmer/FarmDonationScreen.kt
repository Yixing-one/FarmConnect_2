package com.example.farmconnect.ui.farmer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.farmconnect.R
import com.example.farmconnect.ui.theme.FarmConnectTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.farmconnect.ui.charity.FarmViewModel
import com.example.farmconnect.ui.charity.Post


@Composable
//reference from code: https://github.com/Spikeysanju/Wiggles/blob/main/app/src/main/java/dev/spikeysanju/wiggles/component/ItemDogCard.kt
fun DonationPostCard(post: Post, modifier: Modifier = Modifier){
    Card(
        modifier = Modifier
            .width(410.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = {})
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
//            val image: Painter = painterResource(id = post.imageId)
//            Image(
//                modifier = Modifier
//                    .size(80.dp, 80.dp)
//                    .clip(RoundedCornerShape(16.dp)),
//                painter = image,
//                alignment = Alignment.CenterStart,
//                contentDescription = "",
//                contentScale = ContentScale.Crop
//            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.align(Alignment.CenterVertically).size(200.dp, 120.dp)) {
                Text(
                    text = "${post.item_name}  ${post.item_amount} kg",
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
                    style = TextStyle(
                        fontSize = 21.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${post.charity_name}",
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${post.charity_location}",
                    modifier = Modifier.padding(0.dp, 0.dp, 19.dp, 0.dp),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                )

                Row(verticalAlignment = Alignment.Bottom) {

                    val location: Painter = painterResource(id = R.drawable.ic_location)

                    Icon(
                        painter = location,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp, 16.dp),
                        tint = Color.Red
                    )

                    Text(
                        text = "${post.charity_distance} km",
                        modifier = Modifier.padding(8.dp, 3.dp, 5.dp, 0.dp),
                        style = TextStyle(
                            fontSize = 15.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            Column() {
                Button(onClick = {}) {
                    Text(
                        text = "DONATE",
                        modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp).size(10.dp, 110.dp),
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FarmDonationScreen (){
    val viewModel = viewModel<FarmViewModel>()
    val CharityPosts by viewModel.posts.collectAsState()
    val searchText by viewModel.searchText.collectAsState()

    Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Row{
            TextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Search") },
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(700.dp)
        ){
            LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 300.dp)){
                items(CharityPosts.size){item ->
                    DonationPostCard(
                        post = CharityPosts.get(item),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FarmDonationScreePreview() {
    FarmConnectTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            FarmDonationScreen()
        }
    }
}