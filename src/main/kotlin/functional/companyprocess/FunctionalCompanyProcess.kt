package functional.companyprocess

class FunctionalCompanyProcess: CompanyProcess {
    override fun cleanNames(arrayOfNames: Array<String>): String {
        return arrayOfNames.filter { it.length > 1 }
            .map { it.capitalize() }
            .reduce { accumulator, string ->  "$accumulator, $string"}
    }
}