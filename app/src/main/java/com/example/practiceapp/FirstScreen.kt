package com.example.practiceapp

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.practiceapp.ui.theme.PracticeAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//All widgets in column
@Composable
fun FirstScreen(navController: NavController) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerContent()
        },
        topBar = {
           AppBarContent(scope, scaffoldState)
        },
        content = {
            ContentWidget(navController = navController)
        })
}

@Composable
fun DrawerContent() {
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            shape= RectangleShape,
            backgroundColor = Red
        ) {}
        Column {
            screens.forEach { screen ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.AddCircle,
                        contentDescription = ""
                    )
                    Text(" " + screen.title)
                }
            }
        }
    }
}

@Composable
fun AppBarContent(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(
        title = {
            Text(text = "Dashboard")
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, "")
            }
        },
        backgroundColor = Color.Green,
        contentColor = Color.White
    )
}

@Composable
fun ContentWidget(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(20.dp, 30.dp)
            .verticalScroll(rememberScrollState())

    ) {
        TargetsListWidget()
        Spacer(modifier = Modifier.height(10.dp))
        ShiftHoursWidget("10:00 AM", "00:15:05")
        Spacer(modifier = Modifier.height(10.dp))
        PendingHeading()
        Spacer(modifier = Modifier.height(10.dp))
        PendingListWidget(
            listOf(
                PendingItemModel("Nazimabad", 5),
                PendingItemModel("PECHS", 4),
                PendingItemModel("Tariq Road", 3)
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        TodaysRoutePlan(navController = navController)
        Spacer(modifier = Modifier.height(15.dp))
        RoutesListWidget(
            listOf(
                RoutesItemData(
                    "Saleem Shop",
                    "Nazimabad",
                    true
                ),
                RoutesItemData(
                    "City Shop",
                    "F.B Area",
                    false
                ),
                RoutesItemData(
                    "General Store",
                    "Nazimabad",
                    true
                ),
                RoutesItemData(
                    "Bilal Snacks",
                    "Nazimabad",
                    false
                ),
                RoutesItemData(
                    "City Shop",
                    "F.B Area",
                    false
                ),
                RoutesItemData(
                    "General Store",
                    "Nazimabad",
                    true
                ),
                RoutesItemData(
                    "Bilal Snacks",
                    "Nazimabad",
                    false
                ),
            )
        )
    }
}

//targets visited achieved row
@Composable
fun TargetsListWidget() {
    var count = remember { mutableStateOf(20) }
    var visited = remember { mutableStateOf(10) }
    var acheived = remember { mutableStateOf(5) }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CardWidget(count = "${count.value}", name = "Targets", onTap = { count.value++ })
        CardWidget(count = "${visited.value}", name = "Visited", onTap = { visited.value++ })
        CardWidget(count = "${acheived.value}", name = "Acheived", onTap = { acheived.value++ })
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardWidget(count: String, name: String, onTap: () -> Unit) {
    Card(
        shape = RectangleShape,
        border = BorderStroke(1.dp, Color.Green),
        backgroundColor = Color.White,
        onClick = {
            onTap()
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(10.dp)
                .width(90.dp)
                .height(90.dp)
        ) {
            Text(
                count,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Green,
                fontSize = 30.sp
            )
            Text(name, fontSize = 20.sp)

        }
    }
}

//Shift hours Widget
@Composable
fun ShiftHoursWidget(hoursSpent: String, startTime: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Started at: $startTime",
            fontSize = 13.sp,
            color = Color.Gray
        )
        Text(
            "Hours Spent: $hoursSpent",
            fontSize = 13.sp,
            color = Color.Gray
        )
    }
}

//Pending HeadingWidget
@Composable
fun PendingHeading() {
    Text(
        "Pending",
        fontWeight = FontWeight.ExtraBold,
        color = Color.Gray,
        fontSize = 25.sp
    )
}

//Pending List Widget
data class PendingItemModel(val areaName: String, val pendingCount: Int)

@Composable
fun PendingListWidget(pendingList: List<PendingItemModel>) {
    Card(
        border = BorderStroke(1.dp, Color.LightGray),
        shape = RectangleShape
    ) {
        Column {
            pendingList.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 10.dp, 10.dp, 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(item.areaName, color = Color.Gray)
                    Text(
                        item.pendingCount.toString(),
                        color = Color.Gray,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .border(1.5.dp, Color.Gray, CircleShape)
                            .padding(0.dp, 3.dp)
                    )
                }
                Divider()
            }
        }
    }
}

//Today's route widget
@Composable
fun TodaysRoutePlan(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "Today's Route",
            fontWeight = FontWeight.ExtraBold,
            color = Color.Gray,
            fontSize = 25.sp
        )
        Button(
            onClick = {

                navController.navigate("main_2")
            },
            shape = RoundedCornerShape(7.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Green,
                contentColor = Color.White,
            ),
            modifier = Modifier
                .width(70.dp)
                .height(33.dp)
        ) {
            Text(
                "Plan",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

//lazy routes list
data class RoutesItemData(val areaName: String, val shopName: String, val isCompleted: Boolean)

@Composable
fun RoutesListWidget(routesList: List<RoutesItemData>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        itemsIndexed(routesList) { index, item ->
            Card(
                border = BorderStroke(
                    color = Color.Green,
                    width = 1.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .height(130.dp)
                        .width(120.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        (index + 1).toString(),
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Gray
                    )
                    Text(
                        item.shopName,
                        color = if (item.isCompleted) Color.Gray else Color.Green,
                        fontWeight = FontWeight.ExtraBold
                    )
                    if (item.isCompleted) {
                        Icon(
                            Icons.Rounded.Check,
                            "menu",
                            tint = Color.White,
                            modifier = Modifier.background(Color.Green, CircleShape)
                        )
                    } else {
                        Text(
                            item.areaName,
                            color = Color.Gray
                        )

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PracticeAppTheme {
//        MainScreen1(navController = NavController)
    }
}