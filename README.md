# sample-app-updater-with-github-json-file

## Create Master Data Json file
- Visit your GitHub repository.
- Click on the addon icon on the top right side.
- Click on the `New Repository` and Create new public repository
- Click on the `creating a new file` and create json file with extension type `(Eg. master-data.json)`.


## Json object 
```kotlin
{
  "updateTitle" : "Update available!",
  "updateMessage" : "An update for {appname} is available on Play Store. Please update to version 1.0.1",
  "versionCode" : 1,
  "versionName" : "1.0.0",
  "forceUpdate": false,
  "forceUpdateMessage" : "Please update to version 1.0.1",
  "storeLink" : "https://play.google.com/store/apps"
}
```

## Setup Json url in your project build.gradle(:app)
```kotlin
 buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
            buildConfigField "String", "JSON_URL", "\"https://raw.githubusercontent.com/winechitpaing-codigo/sample-app-updater-with-github-json-file/master/master-data.json\""
        }
        debug {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
            buildConfigField "String", "JSON_URL", "\"https://raw.githubusercontent.com/winechitpaing-codigo/sample-app-updater-with-github-json-file/master/master-data.json\""
        }
    }
```

## Load Json Object 
```kotlin
class MainViewModel : ViewModel() {
    private val moshi: Moshi = Moshi.Builder().build()

    private val _uiEvent = MutableLiveData<Event<MasterData>>()
    val uiEvent: LiveData<Event<MasterData>>
        get() = _uiEvent

    init {
        getMasterData()
    }
    private fun getMasterData() {
        viewModelScope.launch(Dispatchers.IO) {
            val json = URL(BuildConfig.JSON_URL).readText()
            val masterData = moshi.adapter(MasterData::class.java).fromJson(json)!!
            _uiEvent.postValue(Event(masterData))
            Timber.i("MasterData:", masterData.toString())
        }
    }
}
```

## Extension functions 
```kotlin
fun MaterialAlertDialogBuilder.setMessage(it: MasterData): MaterialAlertDialogBuilder {
    return if (it.forceUpdate) {
        this.setMessage(it.forceUpdateMessage)
    } else {
        this.setMessage(it.updateMessage)
    }
}

fun MaterialAlertDialogBuilder.setNegativeButton(isForceUpdate: Boolean): MaterialAlertDialogBuilder {
    return if (isForceUpdate) {
        this
    } else {
        this.setNegativeButton(R.string.label_skip) { _, _ -> }
    }
}
```

## App Update Dialog 
```kotlin
MaterialAlertDialogBuilder(this)
                .setTitle(it.updateTitle)
                .setMessage(it)
                .setCancelable(!it.forceUpdate)
                .setPositiveButton(getString(R.string.label_update)) { _, _ ->
                    startActivity(Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(it.storeLink)
                    })
                }
                .setNegativeButton(it.forceUpdate)
                .show()
```

https://raw.githubusercontent.com/winechitpaing-codigo/sample-app-updater-with-github-json-file/master/Screenshot%202022-08-27%20at%203.21.56%20PM.png
https://raw.githubusercontent.com/winechitpaing-codigo/sample-app-updater-with-github-json-file/master/Screenshot%202022-08-27%20at%203.23.01%20PM.png
