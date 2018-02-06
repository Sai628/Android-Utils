package com.sai628.androidutils.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.List;


/**
 * @author Sai
 * @ClassName: ShellUtils
 * @Description: Shell工具类
 * @date 06/02/2018 17:56
 */
public class ShellUtils
{
    private ShellUtils()
    {
        throw new UnsupportedOperationException("You can't instantiate this class.");
    }


    /**
     * 执行命令
     *
     * @param command    命令
     * @param isNeedRoot 是否需要root权限执行
     * @return
     */
    public static CommandResult execCmd(String command, boolean isNeedRoot)
    {
        return execCmd(new String[]{command}, isNeedRoot, true);
    }


    /**
     * 执行命令
     *
     * @param commands   命令链表
     * @param isNeedRoot 是否需要root权限执行
     * @return
     */
    public static CommandResult execCmd(List<String> commands, boolean isNeedRoot)
    {
        return execCmd(commands == null ? null : commands.toArray(new String[]{}), isNeedRoot, true);
    }


    /**
     * 执行命令
     *
     * @param commands   命令数组
     * @param isNeedRoot 是否需要root权限执行
     * @return
     */
    public static CommandResult execCmd(String[] commands, boolean isNeedRoot)
    {
        return execCmd(commands, isNeedRoot, true);
    }


    /**
     * 执行命令
     *
     * @param command      命令
     * @param isNeedRoot   是否需要root权限执行
     * @param isNeedResult 是否需要结果消息
     * @return
     */
    public static CommandResult execCmd(String command, boolean isNeedRoot, boolean isNeedResult)
    {
        return execCmd(new String[]{command}, isNeedRoot, isNeedResult);
    }


    /**
     * 执行命令
     *
     * @param commands     命令链表
     * @param isNeedRoot   是否需要root权限执行
     * @param isNeedResult 是否需要结果消息
     * @return
     */
    public static CommandResult execCmd(List<String> commands, boolean isNeedRoot, boolean isNeedResult)
    {
        return execCmd(commands == null ? null : commands.toArray(new String[]{}), isNeedRoot, isNeedResult);
    }


    /**
     * 执行命令
     *
     * @param commands     命令数组
     * @param isNeedRoot   是否需要root权限执行
     * @param isNeedResult 是否需要结果消息
     * @return
     */
    public static CommandResult execCmd(String[] commands, boolean isNeedRoot, boolean isNeedResult)
    {
        if (commands == null || commands.length == 0)
        {
            return new CommandResult(-1, null, null);
        }

        int result = -1;
        Process process = null;
        BufferedReader successReader = null;
        BufferedReader errorReader = null;
        StringBuilder successMsg = null;
        StringBuilder errorMsg = null;
        DataOutputStream os = null;

        try
        {
            process = Runtime.getRuntime().exec(isNeedRoot ? "su" : "sh");
            os = new DataOutputStream(process.getOutputStream());
            for (String command : commands)
            {
                if (command != null)
                {
                    os.write(command.getBytes());
                    os.writeBytes("\n");
                    os.flush();
                }
            }
            os.writeBytes("exit\n");
            os.flush();

            result = process.waitFor();
            if (isNeedResult)
            {
                successMsg = new StringBuilder();
                errorMsg = new StringBuilder();
                successReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
                errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));

                String line;
                while ((line = successReader.readLine()) != null)
                {
                    successMsg.append(line);
                }
                while ((line = errorReader.readLine()) != null)
                {
                    errorMsg.append(line);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            CloseUtils.closeIO(os, successReader, errorReader);
            if (process != null)
            {
                process.destroy();
            }
        }

        return new CommandResult(result,
                successMsg == null ? null : successMsg.toString(),
                errorMsg == null ? null : errorMsg.toString());
    }


    public static class CommandResult
    {
        private int result;
        private String successMsg;
        private String errorMsg;


        public CommandResult(int result, String successMsg, String errorMsg)
        {
            this.result = result;
            this.successMsg = successMsg;
            this.errorMsg = errorMsg;
        }


        public int getResult()
        {
            return result;
        }


        public void setResult(int result)
        {
            this.result = result;
        }


        public String getSuccessMsg()
        {
            return successMsg;
        }


        public void setSuccessMsg(String successMsg)
        {
            this.successMsg = successMsg;
        }


        public String getErrorMsg()
        {
            return errorMsg;
        }


        public void setErrorMsg(String errorMsg)
        {
            this.errorMsg = errorMsg;
        }


        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            sb.append("CommandResult:[");
            sb.append("result").append("=").append(getResult()).append(",");
            sb.append("successMsg").append("=").append(getSuccessMsg()).append(",");
            sb.append("errorMsg").append("=").append(getErrorMsg());
            sb.append("]");

            return sb.toString();
        }
    }
}
