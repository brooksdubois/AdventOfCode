package day7

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

sealed class ReadWrite {
    companion object {
        private val homeDir = System.getProperty("user.dir")

        fun writeToFile(fileName: String, content: String) {
            try {
                val file = File("$homeDir/$fileName")
                val fos = FileOutputStream(file)
                fos.write(content.toByteArray())
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        fun readFromFile(fileName: String): String {
            var content = ""
            try {
                val file = File("$homeDir/$fileName")
                val fis = FileInputStream(file)
                val data = ByteArray(file.length().toInt())
                fis.read(data)
                fis.close()
                content = String(data, Charsets.UTF_8)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return content
        }
    }
}
