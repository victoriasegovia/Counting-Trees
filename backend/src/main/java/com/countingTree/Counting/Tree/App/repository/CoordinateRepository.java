package com.countingTree.Counting.Tree.App.repository;

// Coordinate is an @Embeddable and therefore should not have a JpaRepository.
// If you need a repository for coordinates, consider making Coordinate an @Entity.

public interface CoordinateRepository {
    // intentionally left blank to avoid Spring creating a repository bean for an embeddable
}
