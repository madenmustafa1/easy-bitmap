#Easy Bitmap

#####Project gradle

```kotlin
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
 or
 #####Settings gradle
```kotlin
dependencyResolutionManagement {
    repositories {
	    ...
        maven { url "https://jitpack.io" }
    }
}
```

#####App gradle
	
    implementation 'com.github.madenmustafa1.easy-bitmap:easy-bitmap:1.0.4'
    implementation 'com.github.madenmustafa1.easy-bitmap:easy-bitmap-ai:1.0.4'


