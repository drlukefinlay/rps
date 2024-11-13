/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 3.2.1263 on 2024-11-11 17:50:26.

export interface PlayerStrategyDTO {
    playerChoice: Choice;
}

export interface RoundOutcomeDTO {
    playerChoice: Choice;
    computerChoice: Choice;
    outcome: Outcome;
}

export interface MetricCountDTO {
    computerCounts: { [P in Choice]?: number };
}

export interface MetricsDTO {
    totalCount: number;
    playerCounts: { [P in Choice]?: MetricCountDTO };
}

export type Choice = "ROCK" | "PAPER" | "SCISSORS";

export type Outcome = "PLAYER_WIN" | "DRAW" | "COMPUTER_WIN";
