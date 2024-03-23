package com.mangu.tfmjuanma.service;

import com.mangu.tfmjuanma.model.Collocation;
import com.mangu.tfmjuanma.model.PhrasalVerb;
import com.mangu.tfmjuanma.model.Verb;

import java.util.List;

public interface FileService {
    List<Verb> getVerbs();

    List<PhrasalVerb> getPhrasalVerbs();

    List<Collocation> getCollocations();
}
