package com.minjin.musinsa.designsystem.component.consume.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.minjin.musinsa.designsystem.R
import com.minjin.musinsa.designsystem.image.NetworkImage
import com.minjin.musinsa.designsystem.modifier.onClick
import com.minjin.musinsa.model.product.Goods
import com.minjin.musinsa.model.state.ContentUiAction
import com.minjin.musinsa.model.state.UiAction
import java.text.DecimalFormat

@Composable
fun ContentGoodsItem(
    modifier: Modifier = Modifier,
    goods: Goods,
    onUiAction: (UiAction) -> Unit = {},
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .onClick { onUiAction(ContentUiAction.OnClickUrl(goods.linkUrl)) }
    ) {
        Box {
            NetworkImage(
                modifier = Modifier.aspectRatio(1f),
                imageUrl = goods.thumbnailUrl
            )

            if (goods.hasCoupon) {
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .background(Color.Blue.copy(0.7f))
                        .padding(2.dp),
                    text = stringResource(R.string.goods_coupon),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White
                )
            }
        }

        Spacer(Modifier.height(6.dp))

        Text(
            text = goods.brandName,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = getDecimalFormatString(goods.price),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = "${goods.saleRate}%",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
private fun getDecimalFormatString(
    value: Int,
    format: String = stringResource(R.string.decimal_format_not_digit) + stringResource(id = R.string.unit_won)
) = DecimalFormat(format).format(value)

@Preview
@Composable
private fun ContentGoodsItemPreview() {
    MaterialTheme {
        Row {
            ContentGoodsItem(
                goods = Goods(
                    linkUrl = "https://www.musinsa.com/app/goods/2281818",
                    thumbnailUrl = "https://image.msscdn.net/images/goods_img/20211224/2281818/2281818_1_320.jpg",
                    brandName = "아스트랄 프로젝션",
                    price = 39900,
                    saleRate = 50,
                    hasCoupon = true
                )
            )
        }
    }
}