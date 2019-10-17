# RevolutTest

This app is developed for Revolut Android code challenge.

[Demo](demo.gif)

- MVVM & Architecture Components (ViewModel, LiveData) are used in this project.
- It is written in Kotlin.
- Kotlin Coroutines is used for background operations.
- Dagger2 is used for dependency injection. Use case classes are injected into ViewModels.
- Retrofit is used for networking.
- Timber is used for logging and reporting exceptions easily.

## Details

**1. This app includes one page showing currencies. Currencies are being fetched and list is being populated every second.**

Coroutines are used to schedule this task in [FetchCurrenciesUseCase](https://github.com/sembozdemir/RevolutTest/blob/f2eb7a590f312124372c861d563aca108f7f75ce/app/src/main/java/com/sembozdemir/revoluttest/main/usecase/FetchCurrenciesUseCase.kt#L30).

**2. Currency which is on the top of the list is specified as base currency and its value is editable. User may edit this value.**

All currencies are being updated during user is editing base value. MediatorLiveData is used to manage fetching process and calculation together. Please see [MainViewModel](https://github.com/sembozdemir/RevolutTest/blob/master/app/src/main/java/com/sembozdemir/revoluttest/main/MainViewModel.kt#L30) for its implementation. In addition, this calculation use case is extracted to [ConverterUseCase](https://github.com/sembozdemir/RevolutTest/blob/master/app/src/main/java/com/sembozdemir/revoluttest/main/usecase/ConverterUseCase.kt) class.

To prevent notifing whole list item, [DiffUtil](https://github.com/sembozdemir/RevolutTest/blob/master/app/src/main/java/com/sembozdemir/revoluttest/core/extensions/RecyclerViews.kt#L9) and [payloads](https://github.com/sembozdemir/RevolutTest/blob/master/app/src/main/java/com/sembozdemir/revoluttest/main/RatesRecyclerAdapter.kt#L60) are used.

When a user clicks on a currency item, it will go to the top and be specified as base currency.

**3. To reduce complexity, [MainState](https://github.com/sembozdemir/RevolutTest/blob/master/app/src/main/java/com/sembozdemir/revoluttest/main/MainState.kt) and [MainEvent](https://github.com/sembozdemir/RevolutTest/blob/master/app/src/main/java/com/sembozdemir/revoluttest/main/MainEvent.kt) classes are used.**

In this way, [MainActivity](https://github.com/sembozdemir/RevolutTest/blob/master/app/src/main/java/com/sembozdemir/revoluttest/main/MainActivity.kt) only observes LiveData of MainState and populates its views according to the changes. [state](https://github.com/sembozdemir/RevolutTest/blob/master/app/src/main/java/com/sembozdemir/revoluttest/main/MainViewModel.kt#L19) property is encapsulated by creating private and mutable one in MainViewModel.

MainActivity also sends UI events to MainViewModel by using MainEvent class. MainEvent class is a sealed class. Events are being handled in [MainViewModel#handleEvent(event)](https://github.com/sembozdemir/RevolutTest/blob/master/app/src/main/java/com/sembozdemir/revoluttest/main/MainViewModel.kt#L44) method so that we could provide more clean and less complicated code.

**4. To get currencies as a list, reflection is used.**

We do not have currencies in a list in the response. That is why, properties of [Rates](https://github.com/sembozdemir/RevolutTest/blob/master/app/src/main/java/com/sembozdemir/revoluttest/core/network/model/Rates.kt) model are converted to a list of currencies by using reflection in [MainModelMapper](https://github.com/sembozdemir/RevolutTest/blob/master/app/src/main/java/com/sembozdemir/revoluttest/main/MainModelMapper.kt).

Also, currency codes are used to generate resource ids in order to get currency name and country flag. This logic is extracted to [CurrencyResources](https://github.com/sembozdemir/RevolutTest/blob/master/app/src/main/java/com/sembozdemir/revoluttest/core/util/CurrencyResources.kt) class. There is also an [instrumented test](https://github.com/sembozdemir/RevolutTest/blob/master/app/src/androidTest/java/com/sembozdemir/revoluttest/CurrencyResourcesTest.kt) for this class so that we can guarantee that resource names cannot be changed somehow.

## Libraries

- Retrofit
- Moshi
- Dagger2
- Timber
