package com.mangu.tfmjuanma.service;

import com.mangu.tfmjuanma.model.Adjective;
import com.mangu.tfmjuanma.model.Collocation;
import com.mangu.tfmjuanma.model.Country;
import com.mangu.tfmjuanma.model.Health;
import com.mangu.tfmjuanma.model.Hobby;
import com.mangu.tfmjuanma.model.Method;
import com.mangu.tfmjuanma.model.PhrasalVerb;
import com.mangu.tfmjuanma.model.Verb;

import java.util.List;

public interface FileService {
    List<Verb> getVerbs();

    List<PhrasalVerb> getPhrasalVerbs();

    List<Collocation> getCollocations();

    List<Adjective> getAdjectives();

    List<Hobby> getHobbies();

    List<Country> getCountries();

    List<Health> getHealthElements();

    List<Method> getMethods();
}
