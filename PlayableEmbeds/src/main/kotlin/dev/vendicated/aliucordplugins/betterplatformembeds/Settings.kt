package dev.vendicated.aliucordplugins.betterplatformembeds

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.view.View
import com.aliucord.Utils.createCheckedSetting
import com.aliucord.api.SettingsAPI
import com.aliucord.views.TextInput
import com.aliucord.widgets.BottomSheet
import com.discord.utilities.view.text.TextWatcher
import com.discord.views.CheckedSetting

class Settings(private val settings: SettingsAPI): BottomSheet() {
    companion object {
        public val DEFAULT_ENDPOINT = "https://yt.lemnoslife.com/"
    }

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)

        val ctx = view.context

        TextInput(ctx, "Youtube Operational API Endpoint").run {
            editText.run {
                maxLines = 1
                setText(settings.getString("yaoiEndpoint", DEFAULT_ENDPOINT).toString())
                inputType = InputType.TYPE_CLASS_TEXT

                addTextChangedListener(object : TextWatcher() {
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                    override fun afterTextChanged(s: Editable) {
                        settings.setString("yaoiEndpoint", s.toString())
                    }
                })
            }

            linearLayout.addView(this)
        }

        addCheckedSetting(ctx, "Enable Youtube Embeds", "Enable embed support for youtube links", "youtubeEnabled")
        addCheckedSetting(ctx, "Enable Youtube Clip Embeds",
            "Enable embeds for youtube clips, which require that the Youtube Operational API Endpoint is set and functioning.",
            "youtubeClipsEnabled")
        addCheckedSetting(ctx, "Enable Spotify Embeds", "Enable embed support for spotify track links", "spotifyEnabled")
        addCheckedSetting(ctx, "Enable Generic Video Embeds",
            "Enable embeds for any other links that have videos. This can be exploited to log your IP, exercise caution.",
            "genericEnabled")
    }

    private fun addCheckedSetting(ctx: Context, title: String, subtitle: String, setting: String) {
        val cs = createCheckedSetting(ctx, CheckedSetting.ViewType.SWITCH, title, subtitle)
        cs.isChecked = settings.getBool(setting, true)
        cs.setOnCheckedListener { checked: Boolean? -> settings.setBool(setting, checked ?: true) }
        addView(cs)
    }
}
