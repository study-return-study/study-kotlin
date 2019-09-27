package functional.companyprocess

fun main(args: Array<String>) {
    val arrayOfNames = arrayOf("breadkey", "dahunlim", "a", "nupdoong")

    var companyProcess: CompanyProcess = ImperativeCompanyProcess()
    var result = companyProcess.cleanNames(arrayOfNames)
    println(result)

    companyProcess = FunctionalCompanyProcess()
    result = companyProcess.cleanNames(arrayOfNames)
    println(result)
}