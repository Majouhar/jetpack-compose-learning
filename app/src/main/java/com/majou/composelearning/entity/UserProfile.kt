package com.majou.composelearning.entity

import com.majou.composelearning.R

data class UserProfile (val id:Int,val name:String,val status:Boolean, val pictureUrl:Any)

val userProfileList = arrayListOf(
    UserProfile(1,"John Doe", true, R.drawable.profile_picture),
    UserProfile(2,"Habibi Ali", false, R.drawable.profile_picture2),
    UserProfile(3,"John Doe", true, "https://picsum.photos/50"),
    UserProfile(4,"Habibi Ali", false, "https://picsum.photos/60"),
    UserProfile(5,"John Doe", true, "https://picsum.photos/70"),
    UserProfile(6,"Habibi Ali", false, R.drawable.profile_picture2),
    UserProfile(7,"John Doe", true, R.drawable.profile_picture),
    UserProfile(8,"Habibi Ali", false, R.drawable.profile_picture2),
    UserProfile(9,"John Doe", true, R.drawable.profile_picture),
    UserProfile(10,"Habibi Ali", false, R.drawable.profile_picture2),
    UserProfile(11,"John Doe", true, R.drawable.profile_picture),
    UserProfile(12,"Habibi Ali", false, R.drawable.profile_picture2),
    UserProfile(13,"John Doe", true, R.drawable.profile_picture),
    UserProfile(14,"Habibi Ali", false, R.drawable.profile_picture2),
)