package functional.companyprocess

class ImperativeCompanyProcess: CompanyProcess {
    override fun cleanNames(arrayOfNames: Array<String>): String {
        val builder = StringBuilder()
        for (i in 0 until arrayOfNames.size) {
            if (arrayOfNames[i].length > 1) {
                builder.append(capitalize(arrayOfNames[i]))
                builder.append(", ")
            }
        }

        return builder.substring(0, builder.length - 2).toString()
    }

    private fun capitalize(string: String): String {
        return string.substring(0, 1).toUpperCase() + string.substring(1, string.length)
    }
}