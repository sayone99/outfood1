package com.example.outfood1.outFood3Hye

import com.google.firebase.database.Exclude

// 혜화만의 데이터 클래스가 필요할 것 같아서 따로 생성해놓음. 혜화끼리는 데이터 클래스 공유.
data class outFood3ReviewHye (
    val objectId:String?,
    val name:String? , // 후기 작성한 사용자의 닉네임
    val rating:Long , // 후기 작성한 사용자가 매긴 별점 // 이걸 자료형을 long?으로 해도 될까...?
    val contents:String? // 후기 작성한 사용자가 작성한 후기글
) {

    @Exclude
    fun toMap(): HashMap<String?, Any?> {
        val result: HashMap<String?, Any?> = HashMap()
        result["objectId"] = objectId
        result["name"] = name
        result["rating"] = rating
        result["contents"] = contents
        // 파이어베이스 측에서 코드 상으론 오류가 발생했어도 파이어베이스 실행하면 잘 실행된다는데,, 이건 한번 해봐야 알듯.


        return result
    }
}