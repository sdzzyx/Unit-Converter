package com.example.unitconverter.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

/**
 *** Created by Lenard on 30/12/2024.
 **/

@Composable
fun UnitConverter(){
    //state management to hold and retain values in the composable function
    var inputValue by remember {
        mutableStateOf("")
    }

    var outputValue by remember {
        mutableStateOf("")
    }

    var inputUnit by remember {
        mutableStateOf("Meters")
    }

    var outputUnit by remember {
        mutableStateOf("Meters")
    }

    var iExpanded by remember {
        mutableStateOf(false)
    }

    var oExpanded by remember {
        mutableStateOf(false)
    }

    val conversionFactor = remember { mutableDoubleStateOf(1.00) }
    val oConversionFactor = remember { mutableDoubleStateOf(1.00) }

    fun convertUnits(){
        // ?: elvis operator, an if else shortened or simplify code
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.00
        val result = (inputValueDouble * conversionFactor.doubleValue * 100 / oConversionFactor.doubleValue).roundToInt() / 100.0
        outputValue = result.toString()
    }

    //UI design/layout
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
            },
            label = {
                Text(
                    text = "Enter Value"
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
        ) {
            Box {
                Button(onClick = {
                    iExpanded = true
                }) {
                    Text(text = inputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {
                    iExpanded = false
                }) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "Centimeters")
                        },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.doubleValue = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Meters")
                        },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.doubleValue = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Feet")
                        },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.doubleValue = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Millimeters")
                        },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor.doubleValue = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box{
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "Centimeters")
                        },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.doubleValue = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Meters")
                        },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.doubleValue = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Feet")
                        },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.doubleValue = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Millimeters")
                        },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Millimeters"
                            oConversionFactor.doubleValue = 0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Result: $outputValue $outputUnit",
            fontSize = 20.sp,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Medium)
    }
}

@Composable
@Preview
fun UnitConverterPreview(){
    UnitConverter()
}
