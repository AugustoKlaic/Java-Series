package com.augusto.rediscache.domain;

import java.io.Serializable;

public record Person(Long id, String name, String email) implements Serializable {
}
