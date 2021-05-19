package com.project.dps.service;

import com.project.dps.domain.member.Member;
import com.project.dps.domain.scenario.stage.Stage;
import com.project.dps.domain.log.StageLog;
import com.project.dps.dto.log.StageLogDto;
import com.project.dps.repository.StageLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PocService {

    private final MemberService memberService;
    private final StageService stageService;
    private final EvaluatorService evaluatorService;

    private final StageLogRepository stageLogRepository;

    @Transactional
    public StageLogDto evaluate(Long memberId, Long stageId, String targetUrl, String targetExtension) {

        Member member = memberService.getMemberIfExist(memberId);
        Stage stage = stageService.getStageIfExist(stageId);

        StageLog stageLog = StageLog.builder()
                .stage(stage).member(member)
                .build();

        stageLogRepository.save(stageLog);

        evaluatorService.evaluate(stageLog, targetUrl, targetExtension);

        return StageLogDto.toDto(stageLog);
    }
}
