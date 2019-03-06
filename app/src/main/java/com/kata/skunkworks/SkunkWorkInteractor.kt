package com.kata.skunkworks

class SkunkWorkInteractor(val skunkworkRepo: SkunkWorkRepository) {

    fun findAllSkunkWork(): List<SkunkWork> {
        return skunkworkRepo.findAllSkunkWorks()
    }

}
