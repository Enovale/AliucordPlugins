include(":CheckLinks")
include(":AnimateApngs")
include(":DedicatedPluginSettings")
include(":Themer")
include(":ViewProfileImages")
include(":TapTap")
include(":MessageLinkEmbeds")
include(":EmojiUtility")
include(":PluginDownloader")
include(":Hastebin")
include(":UrbanDictionary")
rootProject.name = "AliucordPlugins"

include(":DiscordStubs")
project(":DiscordStubs").projectDir = File("../repo/DiscordStubs")

include(":Aliucord")
project(":Aliucord").projectDir = File("../repo/Aliucord")