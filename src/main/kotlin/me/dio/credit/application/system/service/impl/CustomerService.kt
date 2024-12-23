package me.dio.credit.application.system.service.impl

import me.dio.credit.application.system.entity.Customer
import me.dio.credit.application.system.repository.CustomerRepository
import me.dio.credit.application.system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRespository :CustomerRepository
) : ICustomerService {
    override fun save(customer: Customer): Customer {
        return this.customerRespository.save(customer)
    }

    override fun findByID(id: Long): Customer {
        return this.customerRespository.findById(id).orElseThrow{
            throw RuntimeException("Id $id not found")
        }
    }

    override fun delete(id: Long) {
        this.customerRespository.deleteById(id)
    }
}