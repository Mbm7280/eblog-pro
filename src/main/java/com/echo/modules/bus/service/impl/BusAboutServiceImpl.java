package com.echo.modules.bus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.echo.config.api.Result;
import com.echo.dto.GetAboutInfoResDTO;
import com.echo.dto.GetWebInfoResDTO;
import com.echo.modules.bus.model.BusAbout;
import com.echo.modules.bus.mapper.BusAboutMapper;
import com.echo.modules.bus.service.BusAboutService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.echo.common.constant.CommonConstant.ABOUT;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
@Service
public class BusAboutServiceImpl extends ServiceImpl<BusAboutMapper, BusAbout> implements BusAboutService {

    @Autowired
    private BusAboutMapper busAboutMapper;

    @Override
    public Result<GetAboutInfoResDTO> getAboutInfo() {
        GetAboutInfoResDTO resDTO = new GetAboutInfoResDTO();
        BusAbout busAbout = busAboutMapper.selectOne(new LambdaQueryWrapper<BusAbout>().eq(BusAbout::getAboutName,ABOUT));
        BeanUtil.copyProperties(busAbout, resDTO);
        return Result.success(resDTO);
    }
}
