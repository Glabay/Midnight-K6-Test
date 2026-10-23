package dev.midnightcoder.users.internal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface AddressRepository extends JpaRepository<Address, UUID> {
}