package br.com.cwi.resetflix.mapper;

import br.com.cwi.resetflix.entity.AtorEntity;
import br.com.cwi.resetflix.entity.DiretorEntity;
import br.com.cwi.resetflix.response.AtoresResponse;
import br.com.cwi.resetflix.response.DiretoresResponse;

import java.util.ArrayList;
import java.util.List;

public class DiretoresResponseMapper {

    public List<DiretoresResponse> mapeard(final List<DiretorEntity> diretores) {
        List<DiretoresResponse> diretoresResponses = new ArrayList<>();

        for(DiretorEntity diretorEntity : diretores){

            DiretoresResponse diretoresResponse = new DiretoresResponse(diretorEntity.getId(),
                diretorEntity.getNome());

            diretoresResponses.add(diretoresResponse);
        }

        return diretoresResponses;
    }

    public DiretoresResponse mapear(final DiretorEntity diretorEntity) {
        DiretoresResponse diretoresResponse = new DiretoresResponse(diretorEntity.getId(),
                diretorEntity.getNome());
        return diretoresResponse;
    }
}
