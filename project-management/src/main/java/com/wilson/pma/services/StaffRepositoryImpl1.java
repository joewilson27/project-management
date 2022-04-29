package com.wilson.pma.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

//@Primary // jika ada 2 class yg meng-implement ke interface yg sama, kita harus menambahkan @Primary agar component scanning tahu mana yg harus di inject
@Repository
public class StaffRepositoryImpl1 implements IStaffRepository {

}
