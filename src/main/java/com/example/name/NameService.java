package com.example.name;

import org.springframework.stereotype.Service;

@Service
public class NameService {

    private final NameMapper nameMapper;

    public NameService(NameMapper nameMapper) {
        this.nameMapper = nameMapper;
    }

    public Name findById(int id) throws NameNotFoundException {
        return nameMapper.findById(id)
                .orElseThrow(() -> new NameNotFoundException("指定されたIDの名前は存在しません"));
    }

    public Name insert(String name, Integer age) {
        Name newName = new Name(null, name, age);
        nameMapper.insert(newName);
        return newName;
    }

    public Name update(int id, String newName, int newAge) throws NameNotFoundException {

        Name existingName = findById(id);

        existingName.setName(newName);
        existingName.setAge(newAge);

        nameMapper.update(id, newName, newAge);

        return existingName;
    }
}
