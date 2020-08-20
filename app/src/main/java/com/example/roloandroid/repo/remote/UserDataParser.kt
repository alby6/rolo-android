package com.example.roloandroid.repo.remote

import android.service.autofill.UserData
import com.example.roloandroid.data.Company
import com.example.roloandroid.data.User
import okhttp3.ResponseBody
import okhttp3.internal.toImmutableList
import org.json.JSONObject


class UserDataParser {

    companion object  {

        fun parseData(response : ResponseBody) : List<User> {
            val str = response.string()
            val obj = JSONObject(str)
            val contacts = obj.getJSONArray("contacts")

            val users = mutableListOf<User>()
            for (i in 0 until contacts.length()) {
                val contact = contacts.getJSONObject(i)
                val company = contact.getJSONObject("company")
                users.add(
                    User(
                        contact.getInt("id"),
                        contact.getString("name"),
                        contact.getString("email"),
                        false,
                        contact.getString("pictureUrl"),
                       null,
                        Company(
                            company.getString("name"),
                            company.getString("catchPhrase")
                        )
                    )
                )

            }
            return users.toImmutableList()
        }

    }



}

