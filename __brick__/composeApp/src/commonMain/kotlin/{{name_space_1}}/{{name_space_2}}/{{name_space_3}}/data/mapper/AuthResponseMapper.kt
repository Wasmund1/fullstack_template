package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.data.mapper

import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.AuthResponseData
import {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.model.AuthResultData

internal fun AuthResponseData.toAuthResultData(): AuthResultData {
    return AuthResultData(id, name, bio, avatar, token, followerCount, followingCount)
}
