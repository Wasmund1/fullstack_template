package {{name_space_1}}.{{name_space_2}}.{{name_space_3}}.security

import io.ktor.util.hex
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private val ALGORITHM = System.getenv("HASH_ALGORITHM")
private val HASH_Key = System.getenv("HASH_SECRET").toByteArray()
private val hMacKey  = SecretKeySpec(HASH_Key, ALGORITHM)
fun String.hashPassword(): String{
val hMac = Mac.getInstance(ALGORITHM)
    hMac.init(hMacKey)
    return hex(hMac.doFinal(this.toByteArray()))
}