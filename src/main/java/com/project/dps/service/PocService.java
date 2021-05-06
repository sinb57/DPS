package com.project.dps.service;

import com.project.dps.domain.Member;
import com.project.dps.domain.Stage;
import com.project.dps.domain.log.StageLog;
import com.project.dps.mapstruct.dto.log.StageLogDto;
import com.project.dps.mapstruct.mapper.MemberMapper;
import com.project.dps.mapstruct.mapper.StageMapper;
import com.project.dps.mapstruct.mapper.log.StageLogMapper;
import com.project.dps.repository.PocRepository;
import com.project.dps.repository.StageLogRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PocService {

    private final MemberService memberService;
    private final StageService stageService;
    private final EvaluatorService evaluatorService;

    private final PocRepository pocRepository;
    private final StageLogRepository stageLogRepository;

    private final MemberMapper memberMapper = Mappers.getMapper(MemberMapper.class);
    private final StageMapper stageMapper = Mappers.getMapper(StageMapper.class);
    private final StageLogMapper stageLogMapper = Mappers.getMapper(StageLogMapper.class);

    public StageLogDto evaluate(Long memberId, Long stageId, String targetUrl) {

        Member member = memberService.getMemberIfExist(memberId);
        Stage stage = stageService.getStageIfExist(stageId);

        StageLog stageLog = evaluatorService.evaluate(stage, member, targetUrl);

        stageLogRepository.save(stageLog);

        return stageLogMapper.toDto(stageLog);
    }
}
