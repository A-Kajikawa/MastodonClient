package com.s24.mastodonclient.ui.toot_edit

import android.app.Application
import android.net.wifi.hotspot2.pps.Credential
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.s24.mastodonclient.repository.TootRepository
import com.s24.mastodonclient.repository.UserCredentialRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class TootEditViewModel(
    private val instanceUrl: String,
    private val username: String,
    private val coroutineScope: CoroutineScope,
    application: Application
) : AndroidViewModel(application) {

    private val userCredentialRepository = UserCredentialRepository(
        application
    )
    val status = MutableLiveData<String>()

    val postComplete = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun postToot(){
        val statusSnapshot = status.value ?: return
        if (statusSnapshot.isBlank()){
            errorMessage.postValue("投稿内容がありません")
            return
        }

        coroutineScope.launch {
            val credential = userCredentialRepository.find(instanceUrl, username)
            if (credential == null){
                return@launch
            }
            val tootRepository = TootRepository(credential)
            tootRepository.postToot(
                statusSnapshot
            )
            postComplete.postValue(true)
        }
    }
}