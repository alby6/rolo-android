package com.example.roloandroid.repo.remote

import com.example.roloandroid.data.Company
import com.example.roloandroid.data.User
import okhttp3.ResponseBody
import org.json.JSONObject


class UserDataParser {

    companion object  {

        fun parseData(response : ResponseBody) : RemoteData {
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
                        Company(
                            company.getString("name"),
                            company.getString("catchPhrase")
                        )
                    )
                )

            }
            return RemoteData(users)
        }

    }



}

