package io.github.mattpvaughn.synergies.features.library

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import io.github.mattpvaughn.synergies.application.APP_NAME
import io.github.mattpvaughn.synergies.data.local.model.Synergy
import io.github.mattpvaughn.synergies.databinding.SynergyListItemBinding
import java.util.*

fun SynergyListItemBinding.bind(synergy: Synergy) {
    title.text = synergy.name
    description.text = synergy.description

    imageLeftLarge.setImageBitmap(null)
    imageRightLarge.setImageBitmap(null)
    imageLeftSmall1.setImageBitmap(null)
    imageLeftSmall2.setImageBitmap(null)
    imageLeftSmall3.setImageBitmap(null)
    imageLeftSmall4.setImageBitmap(null)
    imageLeftSmall5.setImageBitmap(null)
    imageLeftSmall6.setImageBitmap(null)
    imageRightSmall1.setImageBitmap(null)
    imageRightSmall2.setImageBitmap(null)
    imageRightSmall3.setImageBitmap(null)
    imageRightSmall4.setImageBitmap(null)
    imageRightSmall5.setImageBitmap(null)
    imageRightSmall6.setImageBitmap(null)

    val leftImages = synergy.firstList.split("|")
    val rightImages = synergy.secondList.split("|")

    if (leftImages.size >= 2 && synergy.secondList.isEmpty()) {
        Log.i(APP_NAME, "I'm in it")
        bindImage(leftImages[0], imageLeftLarge, synergy)
        bindImage(leftImages[1], imageRightLarge, synergy)
        return
    }

    if (leftImages.size == 1) {
        bindImage(leftImages[0], imageLeftLarge, synergy)
    } else if (leftImages.size <= 2) {
        Log.i(APP_NAME, "Synergy: $synergy, right images = $rightImages")
        bindImage(leftImages[0], imageLeftSmall3, synergy)
        bindImage(leftImages[1], imageLeftSmall4, synergy)
    } else if (leftImages.size > 2) {
        bindImage(leftImages.getOrNull(0) ?: "", imageLeftSmall1, synergy)
        bindImage(leftImages.getOrNull(1) ?: "", imageLeftSmall2, synergy)
        bindImage(leftImages.getOrNull(2) ?: "", imageLeftSmall3, synergy)
        bindImage(leftImages.getOrNull(3) ?: "", imageLeftSmall4, synergy)
        bindImage(leftImages.getOrNull(4) ?: "", imageLeftSmall5, synergy)
        bindImage(leftImages.getOrNull(5) ?: "", imageLeftSmall6, synergy)
    }

    if (rightImages.size == 1) {
        bindImage(rightImages[0], imageRightLarge, synergy)
    } else if (rightImages.size <= 2) {
        bindImage(rightImages[0], imageRightSmall3, synergy)
        bindImage(rightImages[1], imageRightSmall4, synergy)
    } else if (rightImages.size > 2) {
        bindImage(rightImages.getOrNull(0) ?: "", imageRightSmall1, synergy)
        bindImage(rightImages.getOrNull(1) ?: "", imageRightSmall2, synergy)
        bindImage(rightImages.getOrNull(2) ?: "", imageRightSmall3, synergy)
        bindImage(rightImages.getOrNull(3) ?: "", imageRightSmall4, synergy)
        bindImage(rightImages.getOrNull(4) ?: "", imageRightSmall5, synergy)
        bindImage(rightImages.getOrNull(5) ?: "", imageRightSmall6, synergy)
    }
}

private fun SynergyListItemBinding.bindImage(
    imageString: String,
    imageView: ImageView,
    synergy: Synergy
) {
    if (imageString.isEmpty()) {
        return
    }
    try {
        // Cached icons locally for the list to be a conscientious API user
        val name = "a${imageString.removeNonStandardChars()}"
        val resourceId = root.resources.getIdentifier(
            name, "drawable", root.context.packageName
        )
        if (resourceId == 0x0) {
            throw Resources.NotFoundException()
        }
        val drawable = root.resources.getDrawable(resourceId)
        drawable.isFilterBitmap = false
        imageView.setImageDrawable(drawable)
    } catch (e: Exception) {
        val name = "a${imageString.removeNonStandardChars()}"
        Log.i("TAG", "Could not find $imageString -> $name in $synergy")
    }
}

val invalidResourceCharsRegex = Regex("[^a-z0-9_]")

fun String.removeNonStandardChars(): String {
    val input = this.toLowerCase(Locale.ENGLISH)
        .replace(" ", "_")
        .replace("27", "")
        .replace("28", "")
        .replace("29", "")
    return invalidResourceCharsRegex.replace(input, "")
}
