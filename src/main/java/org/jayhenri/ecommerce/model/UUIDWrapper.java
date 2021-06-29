package org.jayhenri.ecommerce.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UUIDWrapper {

    private UUID uuid = UUID.randomUUID();
}
