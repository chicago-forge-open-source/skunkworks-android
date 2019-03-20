package com.kata.skunkworks

class SkunkWorkInteractor(val skunkworkRepo: SkunkWorkRepository) {

    fun findAllSkunkWork(): MutableList<SkunkWork> {
        return skunkworkRepo.findAllSkunkWorks()
    }

    fun addSkunkWork(title: String) {
        skunkworkRepo.addSkunkWork(SkunkWork(title))
    }

    fun deleteSkunkWork(id: Int) {
        skunkworkRepo.deleteSkunkWork(id)
    }

}
