package com.daniel.budgetplanner.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.daniel.budgetplanner.R
import com.daniel.budgetplanner.ui.theme.BackGround
import com.daniel.budgetplanner.utils.ICON_DIALOG_CLOSE

@Composable
fun PolicyDialog (
    onDismiss: () -> Unit
) {
        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(
                dismissOnClickOutside = false)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(BackGround)
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(onClick = { onDismiss() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = ICON_DIALOG_CLOSE,
                            tint = Color.Black
                        )
                    }
                }

                Text(
                    color = Color.Black,
                    text = stringResource(id = R.string.privacy_policy_title),
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    color = Color.Black,
                    text = stringResource(id = R.string.privacy_policy_date),
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                PrivacyPolicyText()

                Spacer(modifier = Modifier.height(16.dp))

                ClickableText(
                    text = AnnotatedString(
                        text = stringResource(id = R.string.privacy_policy_questions),
                        spanStyle = SpanStyle(
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    ),
                    onClick = { }
                )

                Spacer(modifier = Modifier.height(16.dp))

                ContinueButton(text = stringResource(id = R.string.exit)) {
                    onDismiss()
                }
            }
        }
    }

@Composable
fun PrivacyPolicyText() {
    val privacyPolicyText = stringResource(id = R.string.privacy_policy_text).trimIndent()

    Text(
        text = privacyPolicyText,
        color = Color.Black,
        fontSize = 16.sp,
        style = MaterialTheme.typography.body2
    )
}

@Preview
@Composable
fun ShowMeNow(){
    Box (
        modifier = Modifier
            .background(Color.White)
    ){
        PolicyDialog { }
    }
}