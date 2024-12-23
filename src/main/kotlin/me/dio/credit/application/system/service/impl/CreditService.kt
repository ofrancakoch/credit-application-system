package me.dio.credit.application.system.service.impl

import me.dio.credit.application.system.entity.Credit
import me.dio.credit.application.system.repository.CreditRepository
import me.dio.credit.application.system.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*


@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
) : ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findByID(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> {
        return this.creditRepository.findAllByCustomerId(customerId)
    }

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        var credit = this.creditRepository.findByCreditCode(creditCode)
            ?: throw RuntimeException("Creditcode $creditCode not found")
        return if (credit.customer?.id == customerId) credit else throw RuntimeException("Contact Admin")
        }
}
